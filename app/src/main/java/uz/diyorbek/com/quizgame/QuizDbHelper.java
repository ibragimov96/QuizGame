package uz.diyorbek.com.quizgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import uz.diyorbek.com.quizgame.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyQuizGame.db";
    private static final int DATABASE_VERSION = 5;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT," +
                QuestionsTable.COLUMN_OPTION2 + " TEXT," +
                QuestionsTable.COLUMN_OPTION3 + " TEXT," +
                QuestionsTable.COLUMN_OPTION4 + " TEXT," +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("English");
        addCategory(c1);
        Category c2 = new Category("French");
        addCategory(c2);
        Category c3 = new Category("German");
        addCategory(c3);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("He goes to his guitar lessons ....",
                "by underground", "on underground", "with underground", "in underground", 1,
                Question.DIFFICULTY_EASY, Category.ENGLISH);
        addQuestion(q1);
        Question q2 = new Question("George ..... fly to Stockholm tomorrow.",
                "to going", "goes to", "is going to", "go to", 3,
                Question.DIFFICULTY_MEDIUM, Category.ENGLISH);
        addQuestion(q2);
        Question q3 = new Question("I wanted an orange car, but they only had .....",
                "a one red", "one red", "a red one", "a red", 3,
                Question.DIFFICULTY_HARD, Category.ENGLISH);
        addQuestion(q3);
        Question q4 = new Question("..... waiting any longer. They are clearly not coming.",
                "There is no use", "It is no point", "It is no use", "It is usefulness", 1,
                Question.DIFFICULTY_EASY, Category.ENGLISH);
        addQuestion(q4);
        Question q5 = new Question("By this time tomorrow we .... the meeting.",
                "will have", "will have had", "are having", "will had had", 2,
                Question.DIFFICULTY_MEDIUM, Category.ENGLISH);
        addQuestion(q5);
        Question q6 = new Question("“We’ll never be able to do it” said the man to nobody",
                "especially", "specially", "in particular", "himself", 3,
                Question.DIFFICULTY_HARD, Category.ENGLISH);
        addQuestion(q6);
        Question q7 = new Question("World War I began in which year?",
                "1918", "1914", "1915", "1917", 2,
                Question.DIFFICULTY_EASY, Category.HISTORY);
        addQuestion(q7);
        Question q8 = new Question("Adolf Hitler was born in which country?",
                "France", "Germany", "Austria", "Hungary", 3,
                Question.DIFFICULTY_MEDIUM, Category.HISTORY);
        addQuestion(q8);
        Question q9 = new Question("John F. Kennedy was assassinated in:",
                "New York", "Austin", "Miami", "Dallas", 4,
                Question.DIFFICULTY_HARD, Category.HISTORY);
        addQuestion(q9);
        Question q10 = new Question("Who fought in the war of 1812?",
                "Andrew Jackson", "Arthur Wellsley", "Otto von Bismarck", "Napoleon", 1,
                Question.DIFFICULTY_EASY, Category.HISTORY);
        addQuestion(q10);
        Question q11 = new Question("Which general famously stated 'I shall return?'",
                "Bull Halsey", "George Patton", "Douglas MacArthur", "Omar Bradley", 3,
                Question.DIFFICULTY_MEDIUM, Category.HISTORY);
        addQuestion(q11);
        Question q12 = new Question("“American involvement in the Korean War took place in which decade?",
                "1920", "1950", "1960", "1970", 2,
                Question.DIFFICULTY_HARD, Category.HISTORY);
        addQuestion(q12);
        Question q13 = new Question("The Battle of Hastings in 1066 was fought in which country?",
                "France", "Russia", "England", "Norway", 3,
                Question.DIFFICULTY_EASY, Category.HISTORY);
        addQuestion(q13);
        Question q14 = new Question("The keyword used to transfer control from a function back to the calling function is ....",
                "switch", "goto", "go back", "return", 4,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q14);
        Question q15 = new Question("Which of the following is not logical operator?",
                "&", "&&", "||", "!", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q15);
        Question q16 = new Question("In mathematics and computer programming, which is the correct order of mathematical operators?",
                "Addition, Subtraction, Multiplication, Division", "Division, Multiplication, Addition, Subtraction", "Multiplication, Addition, Division, Subtraction", "Addition, Division, Modulus, Subtraction", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q16);
        Question q17 = new Question("Which of the following cannot be checked in a switch-case statement?",
                "character", "integer", "float", "enum", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q17);
        Question q18 = new Question("What function should be used to free the memory allocated by calloc()?",
                "dealloc();", "malloc(variable_name, 0)", "free();", "memalloc(variable_name, 0)", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q18);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ?" +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[] {String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
