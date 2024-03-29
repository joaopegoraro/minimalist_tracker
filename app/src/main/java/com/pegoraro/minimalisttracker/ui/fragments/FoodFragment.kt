package com.pegoraro.minimalisttracker.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.pegoraro.minimalisttracker.R
import com.pegoraro.minimalisttracker.data.model.Food
import com.pegoraro.minimalisttracker.databinding.FoodNutrientViewBinding
import com.pegoraro.minimalisttracker.ui.ModType
import com.pegoraro.minimalisttracker.ui.TrackerViewModel
import com.pegoraro.minimalisttracker.ui.widgets.FoodListView
import com.pegoraro.minimalisttracker.utils.showDialogWithTextField

/**
 * Implements [BaseFragment], with [lockDrawer] and [hasOptionsMenu] set to true
 *
 * The screen that shows when the user selects a food item from [FoodListView]
 *
 * Displays the complete nutritional information for the food, the option to
 * change its serving size, and a button that calls [TrackerViewModel] to
 * either save or edit it
 */
class FoodFragment : BaseFragment<FoodNutrientViewBinding>(
    FoodNutrientViewBinding::inflate,
    lockDrawer = true,
    hasOptionsMenu = true
) {

    /**
     * The [Food] linked to the selected food item
     */
    private lateinit var selectedFood: Food

    override fun applyBinding(v: View): ApplyTo<FoodNutrientViewBinding> = {
        // Assigns the view model selected food to the Fragment selectedFood
        selectedFood = sharedViewModel.selectedFood

        // Set up the NutritionView
        val nutrients = makeNutrients(selectedFood)
        nutrition.makeAdapter(nutrients, true, showDialog())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.food_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.done_button -> {
                setAddButtonClickListener(binding, sharedViewModel.modType)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Helper Methods

    /**
     * The [FoodFragment] *add button* click listener
     *
     * Adds or edits the [selectedFood] depending on whether or not the
     * [TrackerViewModel.modType] is [ModType.ADD] or [ModType.EDIT], and
     * navigates back to [TrackerFragment]
     */
    private fun setAddButtonClickListener(
        binding: FoodNutrientViewBinding,
        modType: ModType
    ) {
        // If the NutritionView is from a individual food, the serving size 
        // is always the first value
        val newServingSize = binding.nutrition.getFirstNutrientValue()
        if (modType == ModType.ADD)
            sharedViewModel.addFood(selectedFood, newServingSize)
        else {
            sharedViewModel.editFood(selectedFood, newServingSize)
        }
        this@FoodFragment.findNavController()
            .navigate(R.id.action_foodFragment_to_trackerFragment)
    }

    /**
     * Retrieves a [MutableList] containing the nutrients of the [selectedFood]
     * @return a [MutableList] of type [Double]
     */
    private fun makeNutrients(food: Food): MutableList<Double> {
        val nutrients = mutableListOf<Double>()
        nutrients.add(food.servingSize.toDouble())
        nutrients.addAll(food.getNutrients())
        return nutrients
    }

    /**
     * Method that is called when the [Food.servingSize] is clicked inside
     * the [binding] recycler view
     *
     * Shows a dialog with an EditText that can change the value of the
     * [selectedFood] serving size
     */
    private fun showDialog(): (View) -> Unit = {
        showDialogWithTextField(
            requireContext(),
            title = resources.getString(R.string.serving_size),
            hint = resources.getString(
                R.string.food_nutrient,
                selectedFood.servingSize.toDouble()
            ),
            positiveText = resources.getString(R.string.save),
            // Changes the selectedFood serving size and updates the recycler
            // view with the new values
            positiveListener = { _, _, servingEditText ->
                val newServingSize = servingEditText.text.toString().toDouble()
                val newFood = selectedFood.copy().edit(newServingSize)
                val nutrients = makeNutrients(newFood)
                binding.nutrition.makeAdapter(
                    nutrients, true,
                    showDialog(), true
                )
            }
        )
    }
}
