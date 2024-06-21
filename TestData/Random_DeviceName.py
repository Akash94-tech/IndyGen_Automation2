from faker import Faker
import random

def generate_device_address():
    # Generate a random 8-digit number
    random_number = random.randint(10000000, 99999999)
    return f"{random_number}"

for _ in range(10):
    print(generate_device_address())