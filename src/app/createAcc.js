curl -X POST http://0.0.0.0:3000/playeraccount \
-H 'Content-Type: application/json' \
-d '{
  "Firstname": "Rich",
  "Lastname": "Martin",
  "Email": "martin@pe.net",
  "Password": "martin",
  "SecretAnswer": "4martian",
  "SecretQuestion": 4
}'




curl -X POST http://0.0.0.0:3000/playeraccount \
-H 'Content-Type: application/json' \
-d '{
  "Firstname": "Joel",
  "Lastname": "Siragher",
  "Email": "joel.siragher@google.com",
  "Password": "jodasi",
  "SecretAnswer": "dungeon",
  "SecretQuestion": 5
}'


