package ilya.chistousov.countcalories.presentation.meal.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ilya.chistousov.countcalories.databinding.FoodItemBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.presentation.meal.recyclerview.diffutils.FoodDiffUtilsItemCallback

class FoodAdapter : ListAdapter<Food, FoodAdapter.FoodViewHolder>(FoodDiffUtilsItemCallback()) {

    class FoodViewHolder(private val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(food: Food) {
            binding.textViewFoodName.text = food.name
            binding.textViewCalories.text = food.calories.toString()
            binding.textViewProteins.text= food.proteins.toString()
            binding.textViewFats.text= food.fats.toString()
            binding.textViewCarbs.text = food.carbs.toString()
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