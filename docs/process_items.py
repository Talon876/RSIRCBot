#!/usr/bin/env python
from collections import defaultdict

with open('items.txt') as f:
    content = f.readlines()
items = defaultdict(int)

for item in content:
    item = item.replace('\n', '')
    items[item] += 1

for item in sorted(items, key=items.get, reverse=True):
    print item
