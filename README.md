# Project-Cost
Calculating Levenshtein distance between two strings

***Question:*** To Calculate Levenshtein distance between two strings with possible 4 operations one at a time – add a letter, delete a letter, substitute a letter and anagram of word. Cost for these operations to be incurred to reach the target word. Word list to be considered to trace the target word. -1 is returned as output when the source word is invalid or not on the word list.  

***Solution:*** As per my understanding of the question, approached problem considering a word list where both source and target word will be present and computing one edit transformation with the given source word to nearest word in the dictionary following other transformations to reach the target. 

***Approach followed:***
⋅⋅* Reading input through scanner – Operation Cost, Source string, Target String, Dictionary is hard coded in the code [can be changed to get the input as file]
⋅⋅* Travel method – Finding neighbors for each word in dictionary with edit distance 1 / Anagram neighbors [Implemented my isAnagram method].
⋅⋅* Levenshtein Method – Designed using dynamic programming concept, which will find edit considering 1 letter at a time . 
⋅⋅* Graph class – creating map for each word in dictionary with the neighbors, where the edit distance between the word and neighbor is 1.  ⋅⋅* Cost calculation – Depth first search over the map with the source and target.

***Runtime:***
Let’s calculate the runtime for this algorithm here. 
⋅⋅* Travel method finds neighbors for each word in dictionary – O(n^2) [n = len(Dictionary)] 
⋅⋅* Levenshtein method considers 2 strings out of dictionary to min distance – O(mn) [m = len(word1), n = len(word2) ]
⋅⋅* Graph – Hashmap entry with the String and its neighbors – O(1)
⋅⋅* Cost Calculation – DFS over the graph – O(n+m) [n = no of nodes/words, m = no of neighbors for each node] , since we are using visited set

***Total Runtime – (n^2)(mn) + (n+m)***
