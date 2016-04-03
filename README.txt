----------- How we decrypt the 7 messages -----------

I. How we solve random substitution ciphers.
	0/ remove all characters before '>' and after '<' inclusive.
	1/ create CountFrequency function (pass two arguments: first argument is input file - cipher text file, and the second is frequency output file)
	2/ create ReplaceChar function (pass two arguments: first argument is input file - cipher text file,  and the second is plain output file)
	3/ looking at the result file from CountFrequency, use character-frequency technique to guess (in this case,most common character is the space character)
	4/ solve munually, replace each character in the ReplaceChar function.

	**Note: The two new created programs (CountFrequency and ReplaceChar) have some hard-coded path, values.

II. How we decrypt message 1:
	Key 12 - brute force key, using the program Ceasar written in part 2. Making sure remove all character before '>' and after '<' inclusive in the cipher text, add code to handle the missing case "index of char found in alphabet less than key"

III. How we decrypt message 2:
	Key 8 - technique: brute force key and using the program ColTrans written in part 2. Making sure remove all character before '>' and after '<' inclusive in the cipher text

IV. How we decrypt message 3:
	Key 23 --> ceasar or columnar transposition technique. Tried ceasar and using Ceasar program written in part 2 to decrypt, make sure remove all characters before '>' and after '<' inclusive --> done

V. How we decrypt message 5:
	Key: Because this cipher text is related to message 2 somehow, so I guess message 2 is the key file ofthis cipher (since message 2 is longer in length, so the key must be). Delete all characters before '>' and after '<' inclusive in the two cipher text files, Using Vernam program written in part 2, brute force offset value, it is 0.

VI. How we decrypt message 6:
	Key: brute force key 5. Using Ceasar and ColTrans written in part 2 to solve. Making sure all characters before '>' and after '<' inclusive deleted.