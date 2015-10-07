package com.leo.test.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * 实现一个ClassAdapter 用于判断何时修改 AccountAsm 类方法
 * @author Leo
 *
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter{

 public String enhancedSuperName;
 
 public AddSecurityCheckClassAdapter(ClassVisitor cv) {
        /*
         * Responsechain 的下一个 ClassVisitor，这里我们将传入 ClassWriter
         * 负责改写后代码的输出
         */
        super(cv);
    }
    /**
     *  重写 visitMethod，访问到 "operation" 方法时
     *  负责改写后代码的输出
     */
 	@Override
    public MethodVisitor visitMethod(final int access, final String name,
        final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            /*
             *  对于 "operation" 方法
             */
            if (name.equals("operation")) {
                /*
                 *  使用自定义 MethodVisitor，实际改写方法内容
                 */
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            }  }
        return wrappedMv;
    }
}