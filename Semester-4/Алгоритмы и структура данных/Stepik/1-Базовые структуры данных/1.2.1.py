def validate_brackets(input_string: str) -> str:
    balance_stack = []
    
    for index, character in enumerate(input_string, start=1):
        if character in '])}':
            if not balance_stack:
                return str(index)
            previous_open = balance_stack.pop()[0]
            if (previous_open == '(' and character != ')') or \
               (previous_open == '[' and character != ']') or \
               (previous_open == '{' and character != '}'):
                return str(index)
        
        if character in '[({':
            balance_stack.append((character, index))
    
    if not balance_stack:
        return "Success"
    
    return str(balance_stack[-1][1])

if __name__ == '__main__':
    user_input = input()
    print(validate_brackets(user_input))
