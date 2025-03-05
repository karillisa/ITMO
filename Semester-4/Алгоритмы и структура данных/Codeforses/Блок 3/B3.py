def determine_winner(entries):
    score_map = {}
    round_results = []
    winning_player = ""

    for entry in entries:
        name, score_str = entry.split()
        score = int(score_str)

        if name in score_map:
            score_map[name] += score
        else:
            score_map[name] = score
            
        round_results.append((name, score_map[name]))

    max_score = max(score_map.values())
    for player, total_score in round_results:
        if total_score >= max_score and score_map[player] == max_score:
            winning_player = player
            break

    print(winning_player)


if __name__ == "__main__":
    number_of_entries = int(input())
    entries = [input() for _ in range(number_of_entries)]
    determine_winner(entries)