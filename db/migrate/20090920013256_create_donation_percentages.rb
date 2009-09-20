class CreateDonationPercentages < ActiveRecord::Migration
  def self.up
    create_table :donation_percentages do |t|
      t.integer :donation_id
      t.integer :percentage
      t.integer :cause_id

      t.timestamps
    end
  end

  def self.down
    drop_table :donation_percentages
  end
end
