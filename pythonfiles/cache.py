from sqlalchemy import create_engine, Column, String, Integer
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from sqlalchemy.orm.session import Session

# Step 1: Define the Cache ORM model
Base = declarative_base()

class Cache(Base):
    __tablename__ = 'cache'

    Owner = Column(String(24), primary_key=True, nullable=False)
    TaskForceTag = Column(String(24), primary_key=True, nullable=False)
    Shortname = Column(String(24))
    Quantity = Column(Integer)

    def __repr__(self):
        return f"<Cache(Owner='{self.Owner}', TaskForceTag='{self.TaskForceTag}', Shortname='{self.Shortname}', Quantity={self.Quantity})>"

# Replace 'sqlite:///:memory:' with your actual database connection string
engine = create_engine('sqlite:///:memory:')
Base.metadata.create_all(engine)

# Step 2: Establish a session
Session = sessionmaker(bind=engine)
session = Session()

# Step 3: Add a Cache object to the database
new_cache = Cache(Owner='SHIP0003', TaskForceTag='TASK0014', Shortname='PE', Quantity=100)
session.add(new_cache)
session.commit()

# Step 4: Query the database to retrieve the object
retrieved_cache = session.query(Cache).filter_by(Owner='SHIP0003', TaskForceTag='TASK0014').first()

# Step 5: Display the retrieved object's attributes
if retrieved_cache:
    print(f'Retrieved Cache: {retrieved_cache}')
else:
    print('Cache not found.')
