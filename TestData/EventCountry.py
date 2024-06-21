import random

# List of words to generate random company names
words = [
    "Hitachi", "Systems", "India", "Pvt", "Ltd", 
    "Tech", "Global", "Solutions", "International", 
    "Enterprises", "Innovations", "Group", "Services", 
    "Consulting", "Networks", "Software", "Business", 
    "Enterprises", "Technologies"
]

def generate_company_name():
    # Randomly select words and join them to form a company name
    company_name = ' '.join(random.choices(words, k=random.randint(3, 5)))
    return company_name

# Generate a few sample company names
for _ in range(5):
    print(generate_company_name())
