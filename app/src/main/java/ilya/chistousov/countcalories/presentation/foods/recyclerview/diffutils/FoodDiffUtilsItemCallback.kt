package ilya.chistousov.countcalories.presentation.foods.recyclerview.diffutils

import androidx.recyclerview.widget.DiffUtil
import ilya.chistousov.countcalories.domain.model.Food

class FoodDiffUtilsItemCallback : DiffUtil.ItemCallback<Food>() {

    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

}