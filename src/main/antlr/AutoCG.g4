grammar AutoCG;

parse: (stmt_list | error)* EOF;

error:
	UNEXPECTED_CHAR {
     throw new RuntimeException("UNEXPECTED_CHAR=" + $UNEXPECTED_CHAR.text);
   };

stmt_list: SCOL* stmt ( SCOL+ stmt)* SCOL*;

stmt:
	create_class_room_stmt
	| create_course_stmt
	| create_lecture_stmt;

create_class_room_stmt:
	K_CREATE K_CLASSROOM (K_IF K_NOT K_EXISTS)? class_room_name (
		OPEN_PAR config_def (COMMA config_def)* CLOSE_PAR
	);

create_course_stmt:
	K_CREATE K_COURSE (K_IF K_NOT K_EXISTS)? course_name (
		OPEN_PAR config_def (COMMA config_def)* CLOSE_PAR
	);

create_lecture_stmt:
	K_CREATE K_LECTURER (K_IF K_NOT K_EXISTS)? lecture_name (
		OPEN_PAR lecture_def (COMMA lecture_def)* CLOSE_PAR
	);

config_def: config_name value_name;
lecture_def:
	day_name OPEN_PAR value_lecture (COMMA value_lecture)* CLOSE_PAR;

class_room_name: any_name;
course_name: any_name;
configuration_name: any_name;
config_name: any_name;
lecture_name: any_name;

value_name: any_name;
value_lecture:
	| NUMERIC_LITERAL MINUS NUMERIC_LITERAL
	| OPEN_PAR value_lecture CLOSE_PAR;

any_name:
	IDENTIFIER
	| STRING_LITERAL
	| NUMERIC_LITERAL
	| OPEN_PAR any_name CLOSE_PAR;

day_name:
	D_MONDAY
	| D_TUESDAY
	| D_WEDNESDAY
	| D_THURSDAY
	| D_FRIDAY;

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

D_MONDAY: M O N D A Y;
D_TUESDAY: T U E S D A Y;
D_WEDNESDAY: W E D N E S D A Y;
D_THURSDAY: T H U R S D A Y;
D_FRIDAY: F R I D A Y;

K_CREATE: C R E A T E;
K_COURSE: C O U R S E;
K_CLASSROOM: C L A S S R O O M;
K_EXISTS: E X I S T S;
K_IF: I F;
K_LECTURER: L E C T U R E R;
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

MULTILINE_COMMENT: '/*' .*? ('*/' | EOF) -> channel(HIDDEN);

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
