if ! type "twine" > /dev/null; then
  # install foobar here
  echo "You must install Twine to continue ([sudo] gem install twine)"
  exit 1
fi

twine generate-all-localization-files translations/strings.txt app/src/main/res --tags android --format android

grep -rl '&amp;' app/src/main/res | xargs sed -i "s/\&amp;/\&/g"
