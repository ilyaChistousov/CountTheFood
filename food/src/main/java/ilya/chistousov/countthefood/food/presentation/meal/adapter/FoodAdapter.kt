package ilya.chistousov.countthefood.food.presentation.meal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Meal
import ilya.chistousov.food.R
import ilya.chistousov.food.databinding.FoodItemBinding

class FoodAdapter(
    private val clickListener: RecyclerViewOnItemClickListener
) : ListAdapter<Food, FoodAdapter.FoodViewHolder>(FoodDiffUtilsItemCallback()) {

    inner class FoodViewHolder(private val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(food: Food) {
            with(binding) {
                textViewFoodName.text = food.name
                textViewCalories.text =
                    String.format(root.context.getString(R.string.food_item_calories), food.calories, food.grams)
                binding.root.setOnClickListener {
                    clickListener.onItemClick(adapterPosition)
                }
                if (food.meal != Meal.NONE) {
                    addFoodImage.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FoodItemBinding.inflate(layoutInflater, parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}

class FoodDiffUtilsItemCallback : DiffUtil.ItemCallback<Food>() {

    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}

interface RecyclerViewOnItemClickListener {

    fun onItemClick(position: Int)
}