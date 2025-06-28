rm -rf svn_repo
rm -rf svn_wc1
rm -rf svn_wc2
rm -rf svn_branch3

# Создание необходимых родительских каталогов
mkdir -p "C:/Program Files/Git/lab2/svn"

# Путь к SVN - репозиторию
REPO_URL="file:///C:/Program Files/Git/lab2/svn/svn_repo"

# Путь к директории с коммитами
COMMITS_DIR="C:/Program Files/Git/lab2/svn/commits"

# 1. Создание SVN - репозитория
svnadmin create "C:/Program Files/Git/lab2/svn/svn_repo"

# 2. Создание структуры trunk, branches, tags
svn mkdir "$REPO_URL/trunk" -m "Create trunk"
svn mkdir "$REPO_URL/branches" -m "Create branches"
svn mkdir "$REPO_URL/tags" -m "Create tags"

# 3. Создание рабочих копий для master и branch2
svn checkout "$REPO_URL/trunk" "C:/Program Files/Git/lab2/svn/svn_wc1"
svn checkout "$REPO_URL/trunk" "C:/Program Files/Git/lab2/svn/svn_wc2"

# r0 (user1) из commit0
cd "C:/Program Files/Git/lab2/svn/svn_wc1"
cp -r "$COMMITS_DIR/commit0/"* .
svn add --force .
svn commit -m "r0 initial commit by user1 (red)"

# Синхронизация user2
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
svn update

# r1 (user2) из commit1
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
cp -r "$COMMITS_DIR/commit1/"* .
svn commit -m "r1 commit by user2 (blue)"

# r2 (user1) из commit2
cd "C:/Program Files/Git/lab2/svn/svn_wc1"
svn update
cp -r "$COMMITS_DIR/commit2/"* .
svn commit -m "r2 commit by user1"

# r3 (user2) из commit3
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
svn update
cp -r "$COMMITS_DIR/commit3/"* .
svn commit -m "r3 commit by user2"

# r4 (user2) из commit4
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
svn update
cp -r "$COMMITS_DIR/commit4/"* .
svn commit -m "r4 commit by user2"

# r5 (user1) из commit5
cd "C:/Program Files/Git/lab2/svn/svn_wc1"
svn update
cp -r "$COMMITS_DIR/commit5/"* .
svn commit -m "r5 commit by user1"

# r6 (user2) из commit6
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
svn update
cp -r "$COMMITS_DIR/commit6/"* .
svn commit -m "r6 commit by user2"

# r7 (user2) из commit7
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
cp -r "$COMMITS_DIR/commit7/"* .
svn commit -m "r7 commit by user2"

# r8 (user1) из commit8
cd "C:/Program Files/Git/lab2/svn/svn_wc1"
svn update
cp -r "$COMMITS_DIR/commit8/"* .
svn commit -m "r8 commit by user1"

# r9 (user2) из commit9
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
svn update
cp -r "$COMMITS_DIR/commit9/"* .
svn commit -m "r9 commit by user2"

# r10 (user1) из commit10 в branch3
svn copy "$REPO_URL/trunk" "$REPO_URL/branches/branch3" -m "Branch at r9 by user1"
svn checkout "$REPO_URL/branches/branch3" "C:/Program Files/Git/lab2/svn/svn_branch3"
cd "C:/Program Files/Git/lab2/svn/svn_branch3"
cp -r "$COMMITS_DIR/commit10/"* .
svn add --force .
svn commit -m "r10 commit on branch3 by user1"

# r11 (user2) слияние branch3 в branch2
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
svn update
svn merge "$REPO_URL/branches/branch3"
# Если есть конфликты - конфиг файла
# svn resolved --accept=mine-conflict <файл>
svn commit -m "r11 merge branch3 into branch2 by user2"
cp -r "$COMMITS_DIR/commit11/"* .
svn commit -m "r11 commit by user2"

# r12 (user2) из commit12
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
cp -r "$COMMITS_DIR/commit12/"* .
svn commit -m "r12 commit by user2"

# r13 (user2) из commit13
cd "C:/Program Files/Git/lab2/svn/svn_wc2"
cp -r "$COMMITS_DIR/commit13/"* .
svn commit -m "r13 commit by user2"

# r14 (user1) из commit14
cd "C:/Program Files/Git/lab2/svn/svn_wc1"
svn update
cp -r "$COMMITS_DIR/commit14/"* .
svn commit -m "r14 final commit by user1"
