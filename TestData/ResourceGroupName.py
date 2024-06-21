import random

# Define possible segments to construct resource group names
prefixes = ["HSI", "HIS", "Windows", "Sonicwall"]
middle_parts = ["Wensense_Proxy", "Checkpoint_Firewall", "AD_1", "Cisco_Meraki_Firewall", "MSSQL_Server", "Paloalto_Firewall", "mcAfee_epO"]
suffixes = ["", "", "", "", "FW", "Server", "Firewall", "Proxy"]

def generate_resource_group_name():
    # Randomly select segments and combine them to form a resource group name
    prefix = random.choice(prefixes)
    middle = random.choice(middle_parts)
    suffix = random.choice(suffixes)
    if suffix:
        resource_group_name = f"{prefix}_{middle}_{suffix}"
    else:
        resource_group_name = f"{prefix}_{middle}"
    
    # Ensure the name is at most 20 characters long
    return resource_group_name[:20]

# Generate a few sample resource group names
for _ in range(5):
    print(generate_resource_group_name())
