from faker import Faker
import random
from datetime import datetime, timedelta

def generate_random_indexedat(count):
    fake = Faker()
    indexedats = []
    for _ in range(count):
        # Generate a random date within the last year
        start_date = datetime.now() - timedelta(days=365)
        end_date = datetime.now()
        random_timestamp = fake.date_time_between(start_date=start_date, end_date=end_date)
        indexedats.append(random_timestamp.strftime('%Y-%m-%d %H:%M:%S'))

    return indexedats

# Example usage
if __name__ == "__main__":
    count = 10
    indexedats = generate_random_indexedat(count)
    print("Generated random indexedat values:")
    for idx, indexedat in enumerate(indexedats, start=1):
        print(f"{idx}. {indexedat}")
