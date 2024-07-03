from faker import Faker
import random
from datetime import datetime, timedelta

def generate_random_eventtime(count):
    fake = Faker()
    eventtimes = []
    for _ in range(count):
        # Generate a random datetime within the last year
        start_date = datetime.now() - timedelta(days=365)
        end_date = datetime.now()
        random_timestamp = fake.date_time_between(start_date=start_date, end_date=end_date)
        eventtimes.append(random_timestamp.strftime('%Y-%m-%d %H:%M:%S'))

    return eventtimes

# Example usage
if __name__ == "__main__":
    count = 10
    eventtimes = generate_random_eventtime(count)
    print("Generated random eventtime values:")
    for idx, eventtime in enumerate(eventtimes, start=1):
        print(f"{idx}. {eventtime}")
