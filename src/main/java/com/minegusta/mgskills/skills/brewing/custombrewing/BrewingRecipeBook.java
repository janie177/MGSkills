package com.minegusta.mgskills.skills.brewing.custombrewing;


import com.minegusta.mgskills.skills.brewing.custombrewing.recipes.Recipes;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BrewingRecipeBook
{
    private final static ItemStack book = makeBook();

    private static ItemStack makeBook()
    {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta meta = (BookMeta)book.getItemMeta();

        //Make the book
        meta.setTitle(ChatColor.DARK_RED + "Book Of Alchemy");
        meta.setAuthor(ChatColor.RED + "Satan");

        //String seperator = System.getProperty("line.separator");
        String seperator = "\n";

        String[] pages = new String[50];

        int page = 0;

        for(Recipes r : Recipes.values())
        {
            String recipe = r.getRecipe().getInfo();
            pages[page] = (ChatColor.translateAlternateColorCodes('&', recipe.replace("/n", seperator)));
            page++;
            if(page > 50)break;
        }

        meta.addPage(pages);

        //Return
        book.setItemMeta(meta);
        return book;
    }

    public static void openHelp(Player p)
    {
        p.getInventory().addItem(book);
    }
}

