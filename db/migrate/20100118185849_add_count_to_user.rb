class AddCountToUser < ActiveRecord::Migration
  def self.up
    add_column :users, :causes_count, :integer
  end

  def self.down
    remove_column :users, :causes_count
  end
end
