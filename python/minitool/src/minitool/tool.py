# coding=utf8

def replace_spaces_for_tabs(filename, spaces=4):
    """
        把文件中的tab替换为指定数量的空格

        :filename:
            要进行替换的文件路径
        :spaces:
            一个tab替换的空格数量，默认为4
    """
    import re
    content = None

    file = open(filename)
    try:
        content = re.compile('\t', re.DOTALL).sub(" " * spaces, file.read())
    finally:
        file.close()
    
    file = open(filename, "w")
    try:
        file.write(content)
    finally:
        file.close()
        
def main():
    replace_spaces_for_tabs("d:\\pom.xml")

if __name__ == '__main__':
    main();
