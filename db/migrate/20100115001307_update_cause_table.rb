class UpdateCauseTable < ActiveRecord::Migration
  def self.up
    change_table :causes do |t|
      t.integer :user_id
      t.integer :organisation_id
    end
  end

  def self.down
  end
end
