from faker import Faker

import random
import string

fake = Faker()

def generate_account_name():
    # Generate a random first and last name
    first_name = fake.first_name()
    last_name = fake.last_name()
    # Combine with a random 4-digit number
   # random_number = ''.join(random.choices(string.digits))
    # Create an account name
    account_name = f"{first_name}{last_name}"
    return account_name

for _ in range(5):
    print(generate_account_name())