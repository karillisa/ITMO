
rm -rf git_repo
git init  git_repo
cd git_repo


git config --global user.name "red"
git config --global user.email "red@example00.com"
cp ../commits/commit0/* . && git add .
git commit -m "r0"


git checkout -b branch2 # для синих
git config --global user.name "blue"
git config --global user.email "blue@example.com"
cp ../commits/commit1/* . && git add .
git commit -m "r1"


git checkout master
# Внутри репозитория
git config --global user.name "red"
git config --global user.email "red@example00.com"
cp ../commits/commit2/* . && git add .
git commit -m "r2"


git checkout branch2
git config --global user.name "blue"
git config --global user.email "blue@example.com"
cp ../commits/commit3/* . && git add .
git commit -m "r3"


git config --global user.name "blue"
git config --global user.email "blue@example.com"
cp ../commits/commit4/* . && git add .
git commit -m "r4"


git checkout master
# Внутри репозитория
git config --global user.name "red"
git config --global user.email "red@example00.com"
cp ../commits/commit5/* . && git add .
git commit -m "r5"


git checkout branch2
git config --global user.name "blue"
git config --global user.email "blue@example.com"
cp ../commits/commit6/* . && git add .
git commit -m "r6"


cp ../commits/commit7/* . && git add .
git commit -m "r7"


git checkout master
# Внутри репозитория
git config --global user.name "red"
git config --global user.email "red@example00.com"
cp ../commits/commit8/* . && git add . 
git commit -m "r8"

git checkout branch2
git config --global user.name "blue"
git config --global user.email "blue@example.com"
cp ../commits/commit9/* . && git add .
git commit -m "r9"

git checkout -b branch3
git config --global user.name "red"
git config --global user.email "red@example.com"
cp ../commits/commit10/* . && git add .
git commit -m "r10"


git checkout branch2
git merge --no-ff -X ours branch3 -m "метод git merge ours"



git config --global user.name "blue"
git config --global user.email "blue@example.com"
cp ../commits/commit11/* . && git add .
git commit -m "r11"


cp ../commits/commit12/* . && git add .
git commit -m "r12"


cp ../commits/commit13/* . && git add .
git commit -m "r13"


git checkout master
git merge branch2 
cp ../commits/commit14/* . && git add . 
git commit -m "r14"



cd ..
