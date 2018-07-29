package com.nfl.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.Set;

public class ExcelUtil {

    public static void main(String[] args) {
        try {
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File("D:\\contacts.xls"));
            WritableSheet sheet = writableWorkbook.getSheet(0);// 创建新的一页
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // 创建excel文件函数
    // src为待保存的文件路径,json为待保存的json数据
    public static JSONObject createExcel(String src, JSONObject json) {

        JSONObject result = new JSONObject(); // 用来反馈函数调用结果

        try {
            // 新建文件
            File file = new File(src);
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);// 创建工作薄
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);
            WritableSheet sheet = writableWorkbook.createSheet("First sheet", 0);// 创建新的一页
            JSONArray jsonArray = json.getJSONArray("data");// 得到data对应的JSONArray
            Label label; // 单元格对象
            int column = 0; // 列数计数

            // 将第一行信息加到页中。如：姓名、年龄、性别
            JSONObject first = jsonArray.getJSONObject(0);
            Iterator<String> iterator = first.keySet().iterator(); // 得到第一项的key集合
            while (iterator.hasNext()) { // 遍历key集合
                String key = iterator.next(); // 得到key
                label = new Label(column++, 0, key); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值
                sheet.addCell(label); // 将单元格加到页
            }
            // 遍历jsonArray
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = jsonArray.getJSONObject(i); // 得到数组的每项
                iterator = item.keySet().iterator(); // 得到key集合
                column = 0;// 从第0列开始放
                while (iterator.hasNext()) {
                    String key = iterator.next(); // 得到key
                    String value = item.getString(key); // 得到key对应的value
                    label = new Label(column++, (i + 1), value); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值
                    sheet.addCell(label); // 将单元格加到页
                }
            }
            writableWorkbook.write(); // 加入到文件中
            writableWorkbook.close(); // 关闭文件，释放资源
        } catch (Exception e) {
            result.put("result", "failed"); // 将调用该函数的结果返回
            result.put("reason", e.getMessage()); // 将调用该函数失败的原因返回
            return result;
        }
        result.put("result", "successed");
        return result;
    }

    public static void test(String[] args) throws IOException {
        Set<String> keys = null;
        // 创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet0");

        FileReader reader = new FileReader("c://resource.txt");
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        int roleNo = 0;
        int rowNo = 0;
        while ((str = br.readLine()) != null) {
            JSONObject jsonObject = JSONObject.parseObject(str);
            // 创建HSSFRow对象
            HSSFRow row = sheet.createRow(roleNo++);
            // 创建HSSFCell对象
            if (keys == null) {
                //标题
                keys = jsonObject.keySet();
                for (String s : keys) {
                    HSSFCell cell = row.createCell(rowNo++);
                    cell.setCellValue(s);
                }
                rowNo = 0;
                row = sheet.createRow(roleNo++);
            }

            for (String s : keys) {
                HSSFCell cell = row.createCell(rowNo++);
                cell.setCellValue(jsonObject.getString(s));
            }
            rowNo = 0;
            System.out.println(rowNo);

        }

        br.close();
        reader.close();

        // 输出Excel文件
        FileOutputStream output = new FileOutputStream("c://target.xls");
        wb.write(output);
        wb.close();
        output.flush();
        output.close();
    }

}
