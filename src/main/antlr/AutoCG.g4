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

constraint_name: CONS_MAX_LECTURER_HOUR | CONS_RESTRICTED;

create_constraint_stmt: K_CONSTRAINT constraint_name ();

create_class_room_stmt:
	K_CLASSROOM class_room_name (
		OPEN_PAR config_def (COMMA config_def)* CLOSE_PAR
	);

create_course_stmt:
	K_COURSE course_name (
		OPEN_PAR config_def (COMMA config_def)* CLOSE_PAR
	);

create_lecture_stmt:
	K_LECTURER lecture_name (
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
OPEN_PAR: '(';
CLOSE_PAR: ')';
COMMA: ',';
MINUS: '-';

D_MONDAY: M O N D A Y;
D_TUESDAY: T U E S D A Y;
D_WEDNESDAY: W E D N E S D A Y;
D_THURSDAY: T H U R S D A Y;
D_FRIDAY: F R I D A Y;

CONS_RESTRICTED: R E S T R I C T E D;
CONS_MAX_LECTURER_HOUR: L E C T U R E R SPACES H O U R;

K_CONSTRAINT: C O N S T R A I N T;
K_COURSE: C O U R S E;
K_CLASSROOM: C L A S S R O O M;
K_LECTURER: L E C T U R E R;

IDENTIFIER:
	'"' (~'"' | '""')* '"'
	| '`' (~'`' | '``')* '`'
	| '[' ~']'* ']'
	| [a-zA-Z_] [a-zA-Z_0-9]*;

NUMERIC_LITERAL:
	DIGIT+ ('.' DIGIT*)? (E [-+]? DIGIT+)?
	| '.' DIGIT+ ( E [-+]? DIGIT+)?;

STRING_LITERAL: '\'' ( ~'\'' | '\'\'')* '\'';

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
