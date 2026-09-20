import os
import re

BASE = "src/main/java/com/odji/spring_back_end"

class_map = {}
all_fqns = set()
for root, dirs, files in os.walk(BASE):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, 'r', encoding='utf-8') as fp:
            content = fp.read()
        m = re.search(r'^package\s+([\w.]+);', content, re.MULTILINE)
        if m:
            fqn = f"{m.group(1)}.{f[:-5]}"
            class_map[f[:-5]] = fqn
            all_fqns.add(fqn)

print(f"Index: {len(class_map)} classes")

fixed_count = 0
removed_count = 0
added_count = 0

for root, dirs, files in os.walk(BASE):
    for f in files:
        if not f.endswith(".java"):
            continue
        path = os.path.join(root, f)
        with open(path, 'r', encoding='utf-8') as fp:
            content = fp.read()

        own_pkg_m = re.search(r'^package\s+([\w.]+);', content, re.MULTILINE)
        if not own_pkg_m:
            continue
        own_pkg = own_pkg_m.group(1)
        own_cls = f[:-5]

        lines = content.split('\n')
        new_lines = []
        for line in lines:
            m = re.match(r'^import\s+([\w.]+);', line)
            if m:
                fqn = m.group(1)
                if fqn.startswith('com.odji') and fqn not in all_fqns:
                    removed_count += 1
                    continue
            new_lines.append(line)

        content = '\n'.join(new_lines)
        existing_imports = set(re.findall(r'^import\s+([\w.]+);', content, re.MULTILINE))
        existing_simple = {imp.split('.')[-1] for imp in existing_imports}

        clean = re.sub(r'^import .*$', '', content, flags=re.MULTILINE)
        clean = re.sub(r'^package .*$', '', clean, flags=re.MULTILINE)
        clean = re.sub(r'//.*$', '', clean, flags=re.MULTILINE)
        clean = re.sub(r'/\*.*?\*/', '', clean, flags=re.DOTALL)
        clean = re.sub(r'"(?:[^"\\]|\\.)*"', '""', clean)
        clean = re.sub(r"'(?:[^'\\]|\\.)*'", "''", clean)

        used = set(re.findall(r'\b([A-Z][a-zA-Z0-9_]*)\b', clean))

        to_add = []
        for sym in used:
            if sym in existing_simple or sym == own_cls:
                continue
            if sym in class_map:
                fqn = class_map[sym]
                cls_pkg = fqn.rsplit('.', 1)[0]
                if cls_pkg == own_pkg:
                    continue
                if fqn in existing_imports:
                    continue
                to_add.append(fqn)

        if to_add:
            lines = content.split('\n')
            last_import_idx = -1
            for i, line in enumerate(lines):
                if line.startswith('import '):
                    last_import_idx = i
            if last_import_idx >= 0:
                new_imports = [f'import {imp};' for imp in sorted(set(to_add))]
                lines = lines[:last_import_idx+1] + new_imports + lines[last_import_idx+1:]
                content = '\n'.join(lines)
                added_count += len(to_add)

        with open(path, 'w', encoding='utf-8') as fp:
            fp.write(content)
        fixed_count += 1

print(f"{fixed_count} fichiers traites")
print(f"{removed_count} imports casses supprimes")
print(f"{added_count} imports ajoutes")
