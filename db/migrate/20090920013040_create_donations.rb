class CreateDonations < ActiveRecord::Migration
  def self.up
    create_table :donations do |t|
      t.integer :amount
      t.boolean :has_been_payed
      t.integer :user_id
      
      t.timestamps
    end
  end
  
  def self.down
    drop_table :donations
  end
end
