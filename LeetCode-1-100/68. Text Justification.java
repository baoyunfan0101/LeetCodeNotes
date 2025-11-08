// original version
// each word is inherently followed by one space
// dstribute remaining spaces evenly, handling the last line and single-word lines separately
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int len = words.length, start = 0, end = 0;
        List<String> res = new ArrayList<String>();
        while (end < len) {
            // find out where is the end of the line
            int lineWidth = 0, nextLineWidth = words[end].length();
            while (end < len && nextLineWidth <= maxWidth) {
                lineWidth = nextLineWidth;
                if (++end == len)
                    break;
                nextLineWidth += words[end].length() + 1;
            }

            // add the string of the line
            StringBuilder lineBuilder = new StringBuilder();

            // whether it's the last line, and whether there is only one word in the line
            if (end == len || end == start + 1) {
                int leftSpace = maxWidth - lineWidth;
                lineBuilder.append(words[start++]);
                while (start < end) {
                    lineBuilder.append(' ');
                    lineBuilder.append(words[start++]);
                }
                for (int i = 0; i < leftSpace; i++)
                    lineBuilder.append(' ');
            } else {
                // calculate how many spaces needed
                int leftSpace = maxWidth - lineWidth,
                        betweenSpace = leftSpace / (end - start - 1),
                        numOfLongSpace = leftSpace - betweenSpace * (end - start - 1);
                lineBuilder.append(words[start++]);
                while (start < end) {
                    int numOfSpace = numOfLongSpace-- > 0 ? betweenSpace + 2 : betweenSpace + 1;
                    for (int i = 0; i < numOfSpace; i++)
                        lineBuilder.append(' ');
                    lineBuilder.append(words[start++]);
                }
            }
            res.add(lineBuilder.toString());
        }
        return res;
    }
}