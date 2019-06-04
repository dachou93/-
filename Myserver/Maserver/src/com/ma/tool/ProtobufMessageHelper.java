package com.ma.tool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;

public class ProtobufMessageHelper {

	public static Message buildMessage(String msgClassName,  Map<String, String> fields){
        Class cl = null;
        try {
            cl = Class.forName(msgClassName);
            Method method = cl.getMethod("newBuilder");    // newBuilder 为静态变量，即使没有 message 的具体实例也可以 invoke！yes！
            Object obj = method.invoke(null, new Object[]{});
            Message.Builder msgBuilder = (Message.Builder)obj;       // 得到 builder
            return buildMessage(msgBuilder, fields);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static Message buildMessage(Message.Builder builder, Map<String, String> fields) {
        Descriptors.Descriptor descriptor = builder.getDescriptorForType();
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            Descriptors.FieldDescriptor field = getField(descriptor, entry.getKey());
            if (field == null){
                continue;
            }
//            if (entry.getValue() instanceof List<?>) {
//                List<Object> values = (List<Object>) entry.getValue();
//                for (Object value : values) {
//                    builder.addRepeatedField(field, buildValue(builder, field, value));
//                }
//
//            } else {
                builder.setField(field, buildValue(builder, field, entry.getValue()));
//            }
        }
        return builder.build();
    }

    @SuppressWarnings("unchecked")
    private static Object buildValue(
            Message.Builder parentBuilder, Descriptors.FieldDescriptor field, Object value) {
        if (field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) {
            if (field.isRepeated()) {}
            Message.Builder fieldBuilder = parentBuilder.newBuilderForField(field);
            return buildMessage(fieldBuilder, (Map<String, String>) value);
        } else if (field.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
            return field.getEnumType().findValueByName((String) value);
        } else {
            switch (field.getJavaType()) {
                case FLOAT: // float is a special case
                    return Float.valueOf(value.toString());
                case INT:
                    return Integer.valueOf(value.toString());
                case LONG:
                    return Long.valueOf(value.toString());
                case DOUBLE:
                    return Double.valueOf(value.toString());
                default:
                    return value;
            }
        }
    }

    public static Descriptors.FieldDescriptor getField(Descriptors.Descriptor descriptor, String name) {
        return descriptor.findFieldByName(name);
    }
    
    
}
