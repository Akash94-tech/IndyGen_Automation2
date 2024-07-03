from faker import Faker

def generate_policy_ids(count):
    fake = Faker()
    policy_ids = [fake.uuid4()[:8].upper() for _ in range(count)]
    return policy_ids

# Example usage
if __name__ == "__main__":
    count = 10
    policy_ids = generate_policy_ids(count)
    print("Generated Policy IDs:")
    for idx, policy_id in enumerate(policy_ids, start=1):
        print(f"{idx}. {policy_id}")
