from faker import Faker
import random

# Initialize Faker
fake = Faker()

# List of possible components for resource names
resource_components = [
    "sonicwall_FW",
    "HSI_Wensense_Proxy",
    "HIS_Checkpont_fw",
    "Windows_AD",
    "HIS_Cisco_Meraki_fw",
    "HIS_MSSQL_svr",
    "HIS_Paloalto_fw",
    "mcAfee_epO",
    "FW",
    "Proxy",
    "Firewall",
    "AD",
    "Cisco",
    "Meraki",
    "SQL",
    "Paloalto"
]

def generate_resource_name():
    # Select two random components
    component1 = random.choice(resource_components)
    component2 = random.choice(resource_components)
    
    # Combine components ensuring the result is within 20 characters
    resource_name = f"{component1}_{component2}"
    if len(resource_name) > 20:
        resource_name = resource_name[:20]
    
    return resource_name

# Generate a few sample resource names
for _ in range(10):
    print(generate_resource_name())
