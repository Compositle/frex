if output=$(git status --porcelain) && [ -z "$output" ]; then
  echo "Uploading to mod distribution sites"
  cd fabric
  ../gradlew modrinth curseforge --rerun-tasks
  cd ..

  cd forge
  ../gradlew modrinth curseforge --rerun-tasks
  cd ..
else
  echo "Git has uncommitted changes - upload to mod distribution sites cancelled."
fi
