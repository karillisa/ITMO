def input_reader():
    count = int(input())
    entries = [input().split() for _ in range(count)]
    return entries


class Action:
    def perform(self, id, title):
        raise NotImplementedError()


class AddEntry(Action):
    def perform(self, id, title):
        database[id] = title


class RemoveEntry(Action):
    def perform(self, id, title):
        database.pop(id, None)


class SearchEntry(Action):
    def perform(self, id, title):
        return database.get(id, "not found")


command_map = {
    'add': AddEntry(),
    'find': SearchEntry(),
    'del': RemoveEntry()
}

database = {}

if __name__ == '__main__':
    instructions = input_reader()
    for instruction in instructions:
        instruction.append("")
        action = command_map[instruction[0]]
        result = action.perform(instruction[1], instruction[2])
        if result:
            print(result)
