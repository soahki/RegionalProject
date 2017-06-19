package utilities;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParseTables {
    private static JDBCIO io;

    /**
     * municipality_code as foreign key
     * @param tablename the name of the table to be created.
     * @param source the path to the text source from which values will be read.
     */
    public static void parseDataAllInteger(String tablename, String source) {


        try(
                FileInputStream fis = new FileInputStream(source);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
                ) {

            List<String[]> lines = new ArrayList<>();
            String line;
            String[] row;
            String[] columns = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i == 0) {
                    columns = line.split("\t");
                } else {
                    row = line.split("\t");
                    lines.add(row);
                }
                i++;
            }

            JDBCIO io = new JDBCIO();

            // Create a sql statement for creating a table
            String createTable = "CREATE TABLE " + tablename;
            for (int j = 0; j < columns.length; j++) {
                if (j == 0) {
                    createTable += "(id INTEGER PRIMARY KEY," + columns[j] + " STRING";
                } else {
                    createTable += columns[j] + " INTEGER";
                }
                if (j != columns.length-1) {
                    createTable += ",";
                } else {
                    createTable += ", FOREIGN KEY(municipality_code) REFERENCES municipalities(municipality_code))";
                }
            }

            io.insert("DROP TABLE IF EXISTS " + tablename);
            io.insert(createTable);

            // Insert rows into the created table above
            String columnsDefinition = "(";
            for (int k = 0; k < columns.length; k++) {
                columnsDefinition += columns[k];
                if (k != columns.length-1) {
                    columnsDefinition += ", ";
                }
            }
            columnsDefinition += ")";
            for (String[] attributes : lines) {
                String values = "(";
                for (int k = 0; k < attributes.length; k++) {
                    if (k == 0) {
                        values += "'" + attributes[k].trim() + "'";
                    } else {
                        values += attributes[k].trim();
                    }
                    if (k == attributes.length-1) {
                        values += ")";
                    } else {
                        values += ", ";
                    }
                }

                String placeholders = "INSERT INTO %s %s VALUES %s";
                String sql = String.format(placeholders, tablename, columnsDefinition, values);
                io.insert(sql);
                //System.out.println(sql);
                System.out.println("...");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (SQLException se) {
            se.printStackTrace();
        }  catch (IOException ie) {
            ie.printStackTrace();
        }
    }


}
