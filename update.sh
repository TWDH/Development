git stash
git pull
git stash pop
git add . 
time=$(date "+%Y-%m-%d")
echo $time
git commit -m $time
git push