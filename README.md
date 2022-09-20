# An Open Source, Java Truth Table Generator
Handling as many variables as you want, with all elementary operations available.

This project contains the Lexer, the Parser and the Interpreter for it to work properly, all made from scratch for this purpose only.

# How to use it
You can run the given compiled file with `java -jar TruthTable.jar`, you will then be able to write your expressions and get the corresponding truth table.<br />
Quicker, you can run the given compiled file and give your expression as an argument and get the corresponding truth table directly, for exemple, `java -jar TruthTable.jar p->!q` will result in the truth table corresponding to "If p then not q".


# A quick overview
This section will go trough each and every single available operation
## Logic gates
### The NOT operation.
For a given proposition p, "not p" has the opposite truth table. "Not p" is written here either as `!p` or `¬p`. <br>
p  | !p
-- | --
T  | F
F  | T
### The AND operation.
For a given proposition p and q, "p and q" is true only if both p and q are true, and is false otherwise. "p and q" is written here either as `p & q` or `p ∧ q`. <br>
p  | q  | p&q
-- | -- | --
T  | T  | T
T  | F  | F
F  | T  | F
F  | F  | F

### The OR operation.
For a given proposition p and q, "p or q" is true when at least one of p or q is true. "p or q" is written here either as `p | q` or `p ∨ q`. <br>
p  | q  | p&q
-- | -- | --
T  | T  | T
T  | F  | T
F  | T  | T
F  | F  | F

### The XOR operation.
For a given proposition p and q, "p xor q" is true when p and q have different truth values. "p xor q" is written here either as `p + q` or `p ⊕ q`. <br>
p  | q  | p&q
-- | -- | --
T  | T  | F
T  | F  | T
F  | T  | T
F  | F  | F
