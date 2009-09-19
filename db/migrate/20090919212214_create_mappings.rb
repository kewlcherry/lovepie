class CreateMappings < ActiveRecord::Migration
  def self.up
    create_table :mappings do |t|
      t.integer :cause_id
      t.integer :user_id
      t.integer :organisation_id

      t.timestamps
    end
  end

  def self.down
    drop_table :mappings
  end
end
