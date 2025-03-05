def create_login(first_name, last_name):
    login = first_name[0]
    
    for char in first_name[1:]:
        if char >= last_name[0]:
            break
        login += char
        
    login += last_name[0]
    return login

class A2:
    @staticmethod
    def main():
        first, last = input().split()
        print(create_login(first, last))

if __name__ == "__main__":
    A2.main()
    