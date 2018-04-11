#!/bin/bash


git tag --delete 01.00.00

git push --delete origin 01.00.00

git commit -am"dockerbuild.groovy"

git push

git tag  01.00.00

git push origin 01.00.00

exit 0
