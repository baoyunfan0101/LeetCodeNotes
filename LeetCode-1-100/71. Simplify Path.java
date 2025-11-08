// original version: stack
// split the string by '/' (including multiple consecutive '/')
// use a stack to record directory and file names
class Solution {
    public String simplifyPath(String path) {
        List<String> pathList = new ArrayList<String>();
        int i = 0, len = path.length(), fileStart = -1, fileEnd = -1;

        // deal with the trailing part
        path += '/';

        // put into a stack
        while (i <= len) {
            fileEnd = i;
            if (path.charAt(i) == '/') {
                while (++i < len && path.charAt(i) == '/')
                    ;
                if (fileStart >= 0) {
                    String fileName = path.substring(fileStart, fileEnd);
                    if (fileName.equals("."))
                        ;
                    else if (fileName.equals("..")) {
                        if (!pathList.isEmpty())
                            pathList.removeLast();
                    } else
                        pathList.addLast(fileName);
                    fileStart = -1;
                }
                continue;
            }
            if (fileStart < 0)
                fileStart = i;
            i++;
        }

        // output
        if (pathList.isEmpty())
            return "/";

        StringBuilder pathBuilder = new StringBuilder();
        for (String str : pathList) {
            pathBuilder.append('/');
            pathBuilder.append(str);
        }
        return pathBuilder.toString();
    }
}

// original version (modified): stack
// same as the previous version
class Solution {
    public String simplifyPath(String path) {
        List<String> pathList = new ArrayList<String>();
        int i = 0, len = path.length(), fileStart = -1, fileEnd = -1;

        // deal with the trailing part
        path += '/';

        // put into a stack
        while (i <= len) {
            fileEnd = i;
            if (path.charAt(i) == '/') {
                while (++i < len && path.charAt(i) == '/')
                    ;
                if (fileStart >= 0) { // skip starting '/'s
                    String fileName = path.substring(fileStart, fileEnd);
                    if (fileName.equals("."))
                        ;
                    else if (fileName.equals("..")) {
                        if (!pathList.isEmpty())
                            pathList.removeLast();
                    } else
                        pathList.addLast(fileName);
                }
                if (i == len) // if i reaches the end
                    break;
                fileStart = i;
                continue;
            }
            i++;
        }

        // output
        if (pathList.isEmpty())
            return "/";

        StringBuilder pathBuilder = new StringBuilder();
        for (String str : pathList) {
            pathBuilder.append('/');
            pathBuilder.append(str);
        }
        return pathBuilder.toString();
    }
}