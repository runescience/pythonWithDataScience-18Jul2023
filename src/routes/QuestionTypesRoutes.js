import { Router } from "express";
import { QuestionType } from "../entity/QuestionType.js";
import { AppDataSource } from "../config/database.js";
import { v4 as uuidv4 } from "uuid";
import fs from "fs";

const router = Router();

const logToFile = (message) => {
    const logMessage = `${new Date().toISOString()} - ${message}\n`;
    fs.appendFileSync("app.log", logMessage, { flags: "a" });
};

/**
 * Get all question type IDs
 *
 * Endpoint to retrieve all question type IDs from the database.
 * Response:
 * - 200: Array of question type IDs
 * - 500: { error: "Error message" }
 */
router.get("/ids", async (req, res) => {
    try {
        const types = await AppDataSource.manager.find(QuestionType);
        const typeIds = types.map((type) => type.question_type_id);
        res.json(typeIds);
        logToFile(`Fetched ${typeIds.length} question type IDs`);
    } catch (error) {
        logToFile(`Error fetching question type IDs: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

/**
 * Create a new Question Type
 */
router.post("/", async (req, res) => {
    try {
        const {
            type,
            has_regex,
            regex_str,
            has_options,
            options_str,
            has_supplemental,
            supplemental_str,
            author,
        } = req.body;

        // Validate that has_regex and has_options are not both true
        if (has_regex && has_options) {
            return res.status(400).json({ error: "Question type cannot have both regex and options" });
        }

        // Use transaction for creating a new Question Type
        const result = await AppDataSource.manager.transaction(
            async (transactionalEntityManager) => {
                const questionType = await transactionalEntityManager.create(
                    QuestionType,
                    {
                        question_type_id: uuidv4().substring(0, 8),
                        type,
                        has_regex: has_regex !== null ? has_regex : false,
                        regex_str: regex_str || null,
                        has_options: has_options !== null ? has_options : false,
                        options_str: options_str || null,
                        has_supplemental: has_supplemental !== null ? has_supplemental : false,
                        supplemental_str: supplemental_str || null,
                        author,
                    },
                );

                const savedQuestionType = await transactionalEntityManager.save(
                    QuestionType,
                    questionType,
                );
                logToFile(`New Question Type created: ${JSON.stringify(savedQuestionType)}`);
                return savedQuestionType;
            },
        );
        res.status(201).json(result);
    } catch (error) {
        if (error.message.includes("UNIQUE constraint failed")) {
            res.status(409).json({ error: "Question type already exists" });
        } else {
            res.status(500).json({ error: error.message });
        }
        logToFile(`Error creating Question Type: ${error.message}`);
    }
});
/**
 * Get question type by ID with associated questions
 */
router.get("/:id", async (req, res) => {
    try {
        const { id } = req.params;
        const questionType = await AppDataSource.manager
            .createQueryBuilder(QuestionType, "qt")
            .leftJoinAndSelect("qt.questions", "questions")
            .where("qt.question_type_id = :id", { id })
            .getOne();

        if (!questionType) {
            return res.status(404).json({ error: "Question Type not found" });
        }

        res.json(questionType);
        logToFile(`Fetched question type ${id} with its questions`);
    } catch (error) {
        logToFile(`Error fetching question type: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});


router.put("/:id", async (req, res) => {
    const { id } = req.params;
    try {
        const result = await AppDataSource.manager.transaction(
            async (transactionalEntityManager) => {
                const questionType = await transactionalEntityManager.findOne(
                    QuestionType,
                    {
                        where: { question_type_id: id },
                    }
                );

                if (!questionType) {
                    return res.status(404).json({ error: "Question Type not found" });
                }

                const {
                    type,
                    has_regex,
                    regex_str,
                    has_options,
                    options_str,
                    has_supplemental,
                    supplemental_str,
                    author,
                } = req.body;

                // Validate condition for has_regex and has_options
                if (has_regex && has_options) {
                    return res.status(400).json({ error: "Question type cannot have both regex and options" });
                }

                questionType.type = type || questionType.type;
                questionType.has_regex =
                    has_regex !== undefined ? has_regex : questionType.has_regex;
                questionType.regex_str = regex_str || questionType.regex_str;
                questionType.has_options =
                    has_options !== undefined ? has_options : questionType.has_options;
                questionType.options_str = options_str || questionType.options_str;
                questionType.has_supplemental =
                    has_supplemental !== undefined
                        ? has_supplemental
                        : questionType.has_supplemental;
                questionType.supplemental_str =
                    supplemental_str || questionType.supplemental_str;
                questionType.author = author || questionType.author;

                const savedQuestionType = await transactionalEntityManager.save(
                    QuestionType,
                    questionType
                );
                logToFile(`Question Type updated: ${JSON.stringify(savedQuestionType)}`);
                return savedQuestionType;
            }
        );
        res.json(result);
    } catch (error) {
        logToFile(`Error updating Question Type: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

router.delete("/:id", async (req, res) => {
    const { id } = req.params;
    const queryRunner = AppDataSource.createQueryRunner();
    await queryRunner.connect();
    await queryRunner.startTransaction();
    try {
        const questionType = await queryRunner.manager.findOneBy(QuestionType, {
            question_type_id: id,
        });
        if (!questionType) {
            return res.status(404).json({ error: "Question Type not found" });
        }

        await queryRunner.manager.remove(QuestionType, questionType);
        await queryRunner.commitTransaction();
        logToFile(`Question Type deleted: ${id}`);
        res.status(204).send();
    } catch (error) {
        await queryRunner.rollbackTransaction();
        logToFile(`Error deleting Question Type: ${error.message}`);
        res.status(500).json({ error: error.message });
    } finally {
        await queryRunner.release();
    }
});

router.get("/", async (req, res) => {
    try {
        const { active } = req.query;
        let questionTypes;

        logToFile(`Logging get all Question Types: `);

        if (active !== undefined) {
            questionTypes = await AppDataSource.manager.find(QuestionType, {
                where: { is_active: active === "true" },
            });
        } else {
            questionTypes = await AppDataSource.manager.find(QuestionType);
        }

        res.json(questionTypes);
        logToFile(`Fetched Question Types: ${questionTypes.length} found`);
    } catch (error) {
        logToFile(`Error fetching Question Types: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

export default router;
