import random

def generate_random_categories(count):
    categories = ['Electronics', 'Clothing', 'Books', 'Toys', 'Home Appliances', 
                  'Sports', 'Beauty', 'Furniture', 'Food', 'Garden']

    random_categories = [random.choice(categories) for _ in range(count)]
    return random_categories

# Example usage
if __name__ == "__main__":
    count = 100
    random_categories = generate_random_categories(count)
    print("Generated random categories:")
    for idx, category in enumerate(random_categories, start=1):
        print(f"{idx}. {category}")
