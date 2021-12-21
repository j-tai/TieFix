package ca.jtai.tiefix.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.function.Consumer;
import java.util.function.Function;

public class ConfigScreenBuilder {
    /**
     * Builds a new options screen.
     *
     * @param parent the previous screen that was showing
     * @param config the configuration
     * @return a new options screen
     */
    public static Screen build(Screen parent, Config config) {
        return new ConfigScreenBuilder(parent, config).build();
    }

    private final Config config;
    private final ConfigBuilder builder;
    private final ConfigCategory category;

    private ConfigScreenBuilder(Screen parent, Config config) {
        this.config = config;
        builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(new TranslatableText("options.tiefix.title"))
            .setSavingRunnable(() -> ConfigHelper.writeConfig(config));
        category = builder.getOrCreateCategory(
            new TranslatableText("category.tiefix.main")
        );
    }

    /**
     * Build the options screen. Do not call this method more than once -- assume that it consumes {@code this}.
     */
    private Screen build() {
        addFixToggle("mc2071", c -> c.mc2071_fix, b -> config.mc2071_fix = b);
        addFixToggle("mc122477", c -> c.mc122477_fix, b -> config.mc122477_fix = b);
        category.addEntry(
            builder.entryBuilder()
                .startStrField(
                    indented(new TranslatableText("options.tiefix.mc122477_keys")),
                    config.mc122477_keys
                )
                .setDefaultValue("")
                .setSaveConsumer(value -> config.mc122477_keys = value)
                .build()
        );
        category.addEntry(
            builder.entryBuilder()
                .startTextDescription(
                    new TranslatableText("options.tiefix.mc122477.explanation")
                )
                .build()
        );
        addFixToggle("mc127970", c -> c.mc127970_fix, b -> config.mc127970_fix = b);
        addFixToggle("mc140646", c -> c.mc140646_fix, b -> config.mc140646_fix = b);
        addFixToggle("mc145929", c -> c.mc145929_fix, b -> config.mc145929_fix = b);
        addFixToggle("mc147766", c -> c.mc147766_fix, b -> config.mc147766_fix = b);
        addFixToggle("mc151412", c -> c.mc151412_fix, b -> config.mc151412_fix = b);
        addFixToggle("mc237493", c -> c.mc237493_fix, b -> config.mc237493_fix = b);

        return builder.build();
    }

    /**
     * Add a boolean option for a fix toggle.
     *
     * @param id     the ID of the option, used for translation keys
     * @param getter a function that takes a {@link Config} object and returns the current value of the option
     * @param setter a function that sets the option in {@code this.config}
     */
    private void addFixToggle(
        String id,
        Function<Config, Boolean> getter,
        Consumer<Boolean> setter
    ) {
        var title = new TranslatableText("options.tiefix." + id);
        category.addEntry(
            builder.entryBuilder()
                .startBooleanToggle(title, getter.apply(config))
                .setTooltip(new TranslatableText("options.tiefix." + id + ".bug"))
                .setDefaultValue(getter.apply(Config.DEFAULT))
                .setSaveConsumer(setter)
                .build()
        );
    }

    private static Text indented(Text text) {
        return new LiteralText(" ".repeat(8)).append(text);
    }
}
