Do this if you do not already have the branch on your local repo (check with 'git branch'):
git fetch origin <branch you want to fetch> // do this line and the below line if you only want to pull one branch (i recommend doing this)
git checkout -b <branch you want to fetch> origin/<branch you want to fetch>
git checkout <your branch>
git merge origin/<branch you want to fetch>

If you already have the branch on your local repo
then do this instead: 
git checkout <branch you want to fetch>
git fetch
git checkout <your branch>
git merge origin/<branch you want to fetch>
