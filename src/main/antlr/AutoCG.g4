grammar AutoCG;

// statement: create_class_room_stmt | create_class_stmt | create_lecturer;

create_class_room_stmt:
	K_CREATE K_CLASSROOM (K_IF K_NOT K_EXISTS)? class_room_name (
		OPEN_PAR config_def (COMMA config_def)* CLOSE_PAR (
			K_WITHOUT IDENTIFIER
		)?
	);

create_class_stmt:
	K_CREATE K_CLASS (K_IF K_NOT K_EXISTS)? class_name (
		OPEN_PAR config_def (COMMA config_def)* CLOSE_PAR (
			K_WITHOUT IDENTIFIER
		)?
	);

config_def: config_name value_name?;

keyword: K_CREATE | K_CLASS;

any_name:
	IDENTIFIER
	| keyword
	| STRING_LITERAL
	| NUMERIC_LITERAL
	| OPEN_PAR any_name CLOSE_PAR;

class_room_name: any_name;
class_name: any_name;
configuration_name: any_name;
config_name: any_name;
value_name: any_name;

SCOL: ';';
DOT: '.';
OPEN_PAR: '(';
CLOSE_PAR: ')';
COMMA: ',';
ASSIGN: '=';
STAR: '*';
PLUS: '+';
MINUS: '-';
TILDE: '~';
PIPE2: '||';
DIV: '/';
MOD: '%';
LT2: '<<';
GT2: '>>';
AMP: '&';
PIPE: '|';
LT: '<';
LT_EQ: '<=';
GT: '>';
GT_EQ: '>=';
EQ: '==';
NOT_EQ1: '!=';
NOT_EQ2: '<>';

K_CREATE: C R E A T E;
K_CLASS: C L A S S;
K_CLASSROOM: C L A S S R O O M;
K_EXISTS: E X I S T S;
K_IF: I F;
K_NOT: N O T;
K_WITHOUT: W I T H O U T;

IDENTIFIER:
	'"' (~'"' | '""')* '"'
	| '`' (~'`' | '``')* '`'
	| '[' ~']'* ']'
	| [a-zA-Z_] [a-zA-Z_0-9]*;

NUMERIC_LITERAL:
	DIGIT+ ('.' DIGIT*)? (E [-+]? DIGIT+)?
	| '.' DIGIT+ ( E [-+]? DIGIT+)?;

BIND_PARAMETER: '?' DIGIT* | [:@$] IDENTIFIER;

STRING_LITERAL: '\'' ( ~'\'' | '\'\'')* '\'';

BLOB_LITERAL: X STRING_LITERAL;

SINGLE_LINE_COMMENT: '--' ~[\r\n]* -> channel(HIDDEN);

MULTILINE_COMMENT: '/*' .*? ( '*/' | EOF) -> channel(HIDDEN);

SPACES: [ \u000B\t\r\n] -> channel(HIDDEN);

UNEXPECTED_CHAR: .;

fragment DIGIT: [0-9];

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];
