grammar Cron;

cronEntry : minute ' ' hour ' ' dayOfMonth ' ' month ' ' dayOfWeek ' ' command;
minute : timeEntry;
hour : timeEntry;
dayOfMonth : timeEntry;
month : timeEntry;
dayOfWeek : timeEntry;
timeEntry : allTimes | timeInteval | timeRange | specificTimes;
allTimes: '*';
timeInteval: '*/' NUMBER;
timeRange: NUMBER '-' NUMBER;
specificTimes: NUMBERS;
command : (TEXT | ' ')+;

NUMBERS : NUMBER | (NUMBER ',' NUMBERS);
NUMBER : (DIGIT | DOUBLE_DIGIT);
TEXT : LETTER+;

fragment DIGIT : [0-9];
fragment DOUBLE_DIGIT : DIGIT DIGIT;
fragment LETTER : [A-Za-z] | '/';
