grammar Database;

tables : table+;

table : tablename
        column+;

tablename : TABLEIDENT NAME;

column : COLUMNTYPE NAME;

TABLEIDENT : 'Table';

COLUMNTYPE : 'String' | 'Int' | 'Double' | 'Long';

NAME : [A-Z][a-z];

WS : [ \t\r\n]+ -> skip ;
