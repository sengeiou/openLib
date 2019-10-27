package com.open9527.code.common.databinding.click;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/27 10:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BindingClick<T> {
    private BindingAction execute;
    private BindingConsumer<T> consumer;
    private BindingFunction<Boolean> canExecute0;

    public BindingClick(BindingAction execute) {
        this.execute = execute;
    }

    /**
     * @param execute 带泛型参数的命令绑定
     */
    public BindingClick(BindingConsumer<T> execute) {
        this.consumer = execute;
    }

    /**
     * @param execute     触发命令
     * @param canExecute0 true则执行,反之不执行
     */
    public BindingClick(BindingAction execute, BindingFunction<Boolean> canExecute0) {
        this.execute = execute;
        this.canExecute0 = canExecute0;
    }

    /**
     * @param execute     带泛型参数触发命令
     * @param canExecute0 true则执行,反之不执行
     */
    public BindingClick(BindingConsumer<T> execute, BindingFunction<Boolean> canExecute0) {
        this.consumer = execute;
        this.canExecute0 = canExecute0;
    }

    /**
     * 执行BindingAction命令
     */
    public void execute() {
        if (execute != null && canExecute0()) {
            execute.call();
        }
    }

    /**
     * 执行带泛型参数的命令
     *
     * @param parameter 泛型参数
     */
    public void execute(T parameter) {
        if (consumer != null && canExecute0()) {
            consumer.call(parameter);
        }
    }

    /**
     * 是否需要执行
     *
     * @return true则执行, 反之不执行
     */
    private boolean canExecute0() {
        if (canExecute0 == null) {
            return true;
        }
        return canExecute0.call();
    }

}
