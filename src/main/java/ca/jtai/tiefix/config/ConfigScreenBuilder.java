package ca.jtai.tiefix.config;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<AbstractConfigListEntry<?>> entries;

    private ConfigScreenBuilder(Screen parent, Config config) {
        this.config = config;
        builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(translate("title"))
            .setSavingRunnable(() -> ConfigHelper.writeConfig(config));
        category = builder.getOrCreateCategory( translate("main") );
    }

    /**
     * Build the options screen. Do not call this method more than once -- assume that it consumes {@code this}.
     */
    private Screen build() {
        beginCategory();
        addFixToggle("mc2071", c -> c.mc2071_fix, b -> config.mc2071_fix = b);
        addFixToggle("mc62997", c -> c.mc62997_fix, b -> config.mc62997_fix = b);
        addFixToggle("mc89242", c -> c.mc89242_fix, b -> config.mc89242_fix = b);
        addFixToggle("mc122477", c -> c.mc122477_fix, b -> config.mc122477_fix = b);
        entries.add(
            builder.entryBuilder()
                .startStrField(
                    indented(translate("mc122477_keys")),
                    config.mc122477_keys
                )
                .setDefaultValue("")
                .setSaveConsumer(value -> config.mc122477_keys = value)
                .build()
        );
        addDescription("mc122477.explanation");
        addFixToggle("mc140646", c -> c.mc140646_fix, b -> config.mc140646_fix = b);
        addFixToggle("mc145929", c -> c.mc145929_fix, b -> config.mc145929_fix = b);
        addFixToggle("mc147766", c -> c.mc147766_fix, b -> config.mc147766_fix = b);
        addFixToggle("mc151412", c -> c.mc151412_fix, b -> config.mc151412_fix = b);
        addFixToggle("mc233042", c -> c.mc233042_fix, b -> config.mc233042_fix = b);
        endCategory("ui");

        beginCategory();
        addFixToggle("mc122645", c -> c.mc122645_fix, b -> config.mc122645_fix = b);
        addFixToggle("mc203401", c -> c.mc203401_fix, b -> config.mc203401_fix = b);
        endCategory("controls");

        beginCategory();
        addFixToggle("mc4490", c -> c.mc4490_fix, b -> config.mc4490_fix = b);
        addFixToggle("mc53312", c -> c.mc53312_fix, b -> config.mc53312_fix = b, "reloadToApply");
        addFixToggle("mc127970", c -> c.mc127970_fix, b -> config.mc127970_fix = b);
        endCategory("audioVisual");

        beginCategory();
        addMultiplayerToggle(c -> c.gameplayAllowMultiplayer, b -> config.gameplayAllowMultiplayer = b);
        addFixToggle("mc12829", c -> c.mc12829_fix, b -> config.mc12829_fix = b);
        addFixToggle("mc136249", c -> c.mc136249_fix, b -> config.mc136249_fix = b);
        endCategory("gameplay");

        beginCategory();
        addFixToggle("mc237493", c -> c.mc237493_fix, b -> config.mc237493_fix = b);
        endCategory("misc");

        return builder.build();
    }

    private void beginCategory() {
        entries = new ArrayList<>();
    }

    private void endCategory(String id) {
        category.addEntry(
            builder.entryBuilder()
                .startSubCategory(translate("category." + id), Collections.unmodifiableList(entries))
                .setExpanded(true)
                .build()
        );
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
        Consumer<Boolean> setter,
        String... extraTooltips
    ) {
        var title = translate(id);
        var tooltip = translate(id + ".bug");
        for (var extra : extraTooltips) {
            tooltip.append("\n");
            tooltip.append(translate(extra));
        }

        entries.add(
            builder.entryBuilder()
                .startBooleanToggle(title, getter.apply(config))
                .setTooltip(tooltip)
                .setDefaultValue(getter.apply(Config.DEFAULT))
                .setSaveConsumer(setter)
                .build()
        );
    }

    private void addMultiplayerToggle(
        Function<Config, Boolean> getter,
        Consumer<Boolean> setter
    ) {
        addDescription("mayTriggerAnticheat");
        entries.add(
            builder.entryBuilder()
                .startBooleanToggle(translate("enableInMultiplayer"), getter.apply(config))
                .setDefaultValue(getter.apply(Config.DEFAULT))
                .setSaveConsumer(setter)
                .build()
        );
    }

    private void addDescription(String id) {
        entries.add(
            builder.entryBuilder()
                .startTextDescription(translate(id))
                .build()
        );
    }

    private static TranslatableText translate(String id) {
        return new TranslatableText("options.tiefix." + id);
    }

    private static Text indented(Text text) {
        return new LiteralText(" ".repeat(8)).append(text);
    }
}
