def register_users(usernames):
    user_count = {}
    for username in usernames:
        if username in user_count:
            user_count[username] += 1
            print(f"{username}{user_count[username]}")
        else:
            user_count[username] = 0
            print("OK")


if __name__ == "__main__":
    num_users = int(input())
    usernames = []
    for _ in range(num_users):
        username_input = input()
        usernames.append(username_input)
    register_users(usernames)