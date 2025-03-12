#include <bits/stdc++.h>
using namespace std;

const int MOD = 1000000001;

struct Node {
    long long sum;
    Node *left, *right;
    Node() : sum(0), left(nullptr), right(nullptr) {}
    ~Node() {
        delete left;
        delete right;
    }
};

Node* root = nullptr;
unordered_set<int> present;
long long last_sum = 0;

void update(Node* &node, int l, int r, int pos, int val) {
    if (!node) node = new Node();
    if (l == r) {
        node->sum += val;
        return;
    }
    int mid = l + (r - l) / 2;
    if (pos <= mid) {
        update(node->left, l, mid, pos, val);
    } else {
        update(node->right, mid + 1, r, pos, val);
    }
    node->sum = (node->left ? node->left->sum : 0) + (node->right ? node->right->sum : 0);
}

long long query(Node* node, int l, int r, int ql, int qr) {
    if (!node || r < ql || l > qr) return 0; 
    if (ql <= l && r <= qr) return node->sum;
    int mid = l + (r - l) / 2;
    return query(node->left, l, mid, ql, qr) + query(node->right, mid + 1, r, ql, qr);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n;
    cin >> n;

    while (n--) {
        string op;
        cin >> op;
        if (op == "+") {
            int t;
            cin >> t;
            int x = (t + last_sum) % MOD;
            if (!present.count(x)) {
                present.insert(x);
                update(root, 0, MOD - 1, x, x);
            }
        } else if (op == "-") {
            int t;
            cin >> t;
            int x = (t + last_sum) % MOD;
            if (present.count(x)) {
                present.erase(x);
                update(root, 0, MOD - 1, x, -x);
            }
        } else if (op == "?") {
            int t;
            cin >> t;
            int x = (t + last_sum) % MOD;
            cout << (present.count(x) ? "Found" : "Not found") << '\n';
        } else if (op == "s") {
            int t, r_val;
            cin >> t >> r_val;
            int L = (t + last_sum) % MOD;
            int R = (r_val + last_sum) % MOD;
            if (L > R) swap(L, R);
            long long sum = query(root, 0, MOD - 1, L, R);
            last_sum = sum;
            cout << sum << '\n';
        }
    }
    delete root; 

    return 0;
}